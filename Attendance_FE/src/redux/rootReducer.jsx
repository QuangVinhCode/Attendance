import { combineReducers } from "redux";
import classReducer from "./reducers/classReducer";
import teacherReducer from "./reducers/teacherReducer";
import commonReducer from "./reducers/commonReducer";
import studentReducer from "./reducers/studentReducer";
const rootReducer = combineReducers({
  classReducer: classReducer,
  teacherReducer: teacherReducer,
  commonReducer: commonReducer,
  studentReducer: studentReducer,
});

export default rootReducer;
