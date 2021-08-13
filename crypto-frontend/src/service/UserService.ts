import axios from "../integration";
import authHeader from "@/service/AuthHeaders";

class UserService {
    getUserInfo(id: bigint) {
        return axios.get("/users/"+id, {headers: authHeader()})
    }
}

export default new UserService();