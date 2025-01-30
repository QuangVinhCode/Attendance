import { combineReducers } from "redux";
import classReducer from "./reducers/classReducer";
import teacherReducer from "./reducers/teacherReducer";
import commonReducer from "./reducers/commonReducer";
import studentReducer from "./reducers/studentReducer";
import attendanceReducer from "./reducers/attendanceReducer";
const rootReducer = combineReducers({
  classReducer: classReducer,
  teacherReducer: teacherReducer,
  commonReducer: commonReducer,
  studentReducer: studentReducer,
  attendanceReducer:attendanceReducer,
});

export default rootReducer;
