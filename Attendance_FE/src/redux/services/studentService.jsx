import axios from "axios";
import { API_USERS } from "./constant";

export default class StudentService {
  getStudentInfo = async (id) => {
    return await axios.get(API_USERS + "/student-info/" + id);
  };
}
