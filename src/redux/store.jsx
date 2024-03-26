import { configureStore } from "@reduxjs/toolkit";
import rootReducer from "./rootReducer";

const initialState = {};
function configureAppStore(preloadedState) {
  const store = configureStore({
    reducer: rootReducer,
    preloadedState: preloadedState,
  });
  if (process.env.NODE_ENV !== "production" && module.hot) {
    module.hot.accept("./rootReducer");
    store.replaceReducer(rootReducer);
  }
  return store;
}

export default configureAppStore(initialState);

// import { configureStore } from "@reduxjs/toolkit";
// import rootReducer from "./rootReducer";

// const initialState = {};

// function configureAppStore(preloadedState) {
//   const store = configureStore({
//     reducer: rootReducer,
//     preloadedState: preloadedState,
//   });

//   if (process.env.NODE_ENV !== "production" && module.hot) {
//     module.hot.accept("./rootReducer", () => {
//       store.replaceReducer(rootReducer);
//     });
//   }

//   return store;
// }

// export default configureAppStore(initialState);
