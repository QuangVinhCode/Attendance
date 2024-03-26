import { combineReducers } from "redux";
import categoryReducer from "./reducer/categoryReducer";
import commonReducer from "./reducer/commonReducer";

const rootReducer = combineReducers({
  categoryReducer: categoryReducer,
  commonReducer: commonReducer,
});

export default rootReducer;
