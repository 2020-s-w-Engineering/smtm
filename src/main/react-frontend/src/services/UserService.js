import axios from 'axios'
import App from '../App';

const USERS_REST_API_URL = 'http://localhost:8080/users';

const api = axios.create({
    baseURL: 'http://localhost:8080/users'
})

class UserService {

    getUsers() {
        return api.get();
    }

    createUser = async () => {
        let res = await api.post('/', {userId: "test", password: "pass4", isKorean: "true"})

    }

}

export default new UserService()