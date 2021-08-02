import axios from "../integration";

class TestService {
    sayHello() {
        return axios.get("/hello");
    }
}

export default new TestService();