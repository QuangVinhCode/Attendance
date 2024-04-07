import axios from "axios";
import { API_TEACHER } from "./constant";

export default class TeacherService {
  getTeachers = async () => {
    return await axios.get(API_TEACHER);
  };
}
