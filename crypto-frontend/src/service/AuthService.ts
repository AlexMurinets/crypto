import axios from "../integration";

class AuthService {

    login(username: string, password: string) {
        return axios
            .post('/auth/login', {
                username,
                password
            })
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                else {
                    console.log("Bad credentials")
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

}

export default new AuthService();