from bs4 import BeautifulSoup
import requests
import re
import time
import psycopg2


def normalize_float(s):
    res = s
    indx = res.find('.')
    while indx != -1:
        res = res[:indx] + res[indx+1:]
        indx = res.find('.')
    res = res.replace(',', '.')
    return res


DB_HOST = "localhost"
DB_NAME = "crypto"
DB_PASS = "root"
DB_USER = "root"

conn = psycopg2.connect(dbname=DB_NAME, user=DB_USER, password=DB_PASS, host=DB_HOST, port=5431)
c = conn.cursor()

url = "https://ru.investing.com/crypto/"

headers = {
    "Accept": "*/*",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0"
}

q_get_abbr = "SELECT abbreviation FROM currency"
c.execute(q_get_abbr)
abbrs = c.fetchall()

while True:
    req = requests.get(url, headers=headers)
    src = req.text
    soup = BeautifulSoup(src, "lxml")
    for item in abbrs:
        val = soup.find(attrs={"class": "left noWrap elp symb js-currency-symbol", "title": item[0]})
        if val is None:
            continue
        val = val.parent.find(class_="price js-currency-price").text
        val = normalize_float(val)
        c.execute("UPDATE currency SET value = %s WHERE abbreviation = %s;", (float(val.strip().strip("'")), item[0]))
        conn.commit()
    time.sleep(10)

c.close()
conn.close()
