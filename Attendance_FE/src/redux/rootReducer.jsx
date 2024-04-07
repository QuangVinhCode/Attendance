import { combineReducers } from "redux";
import classReducer from "./reducers/classReducer";
import teacherReducer from "./reducers/teacherReducer";
import commonReducer from "./reducers/commonReducer";
const rootReducer = combineReducers({
  classReducer: classReducer,
  teacherReducer: teacherReducer,
  commonReducer: commonReducer,
});

export default rootReducer;
