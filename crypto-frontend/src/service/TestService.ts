import axios from "../integration";
import authHeader from "@/service/AuthHeaders";

class TestService {
    sayHello() {
        return axios.get("/hello");
    }

    saySecuredHello() {
        return axios.get("/securedHello", {headers: authHeader()})
    }
}

export default new TestService();