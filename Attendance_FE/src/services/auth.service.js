import axios from "axios";

export const URL_HTTP = "http://localhost:8080/api/v1";
const KEY_LOCAL = "__AUTH__";
export default class AuthService {
    static isLogin() {
        return localStorage.getItem(KEY_LOCAL) !== null;
    }

    static getUserInfo() {
        try {
            return JSON.parse(localStorage.getItem(KEY_LOCAL));
        } catch (error) {
            return null;
        }
    }

    static logout() {
        localStorage.removeItem("classdata");
        return localStorage.removeItem(KEY_LOCAL);
    }

    static setProfile(data) {
        return localStorage.setItem(KEY_LOCAL, JSON.stringify(data));
    }

    static async Login(email, password) {
        const url = URL_HTTP + `/users/login/${email}/${password}`;
        const { data } = await axios.patch(url);
        AuthService.setProfile(data);
    }
}
