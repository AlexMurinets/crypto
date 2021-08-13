export default function authHeader() {
    const storedUser = localStorage.getItem('user');
    const user = JSON.parse(storedUser ? storedUser : "");

    if (user && user.token) {
        return { Authorization: 'Bearer ' + user.token };
    } else {
        return {};
    }
}