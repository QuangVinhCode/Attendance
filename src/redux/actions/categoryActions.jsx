import CategoryService from "../../services/categoryService";
import {
  CATEGORIES_DELETE,
  CATEGORIES_SET,
  CATEGORIES_STATE_CLEAR,
  CATEGORY_SET,
  COMMON_ERROR_SET,
  COMMON_LOADING_SET,
  COMMON_MESSAGE_SET,
} from "./actionTypes";

export const insertCategory = (category, navigate) => async (dispatch) => {
  const service = new CategoryService();
  //   console.log(service);
  try {
    console.log("insertCategory");
    dispatch({
      type: COMMON_LOADING_SET,
      payload: true,
    });

    const response = await service.insertCategory(category);
    console.log(response);
    if (response.status === 200) {
      dispatch({
        type: CATEGORY_SET,
        payload: response.data,
      });
      dispatch({
        type: COMMON_MESSAGE_SET,
        payload: "Category set successfully",
      });
    } else {
      dispatch({
        type: COMMON_ERROR_SET,
        payload: response.message,
      });
    }
  } catch (error) {
    console.error("Error: " + error);
    dispatch({
      type: COMMON_ERROR_SET,
      payload: error.response.data
        ? error.response.data.message
        : error.message,
    });
  }
  dispatch({
    type: COMMON_LOADING_SET,
    payload: false,
  });
  navigate("/categories/list");
};

export const getCategory = () => async (dispatch) => {
  // getCategories
  const service = new CategoryService();
  try {
    dispatch({
      type: COMMON_LOADING_SET,
      payload: true,
    });
    const response = await service.getCategory();
    console.log(response);
    if (response.status === 200) {
      dispatch({
        type: CATEGORIES_SET,
        payload: response.data,
      });
    } else {
      dispatch({
        type: COMMON_ERROR_SET,
        payload: response.message,
      });
    }
  } catch (error) {
    dispatch({
      type: COMMON_ERROR_SET,
      payload: error.response.data
        ? error.response.data.message
        : error.message,
    });
  }
  dispatch({
    type: COMMON_LOADING_SET,
    payload: false,
  });
};

export const deleteCategory = (id) => async (dispatch) => {
  const service = new CategoryService();
  try {
    dispatch({
      type: COMMON_LOADING_SET,
      payload: true,
    });
    const response = await service.deleteCategory(id);
    console.log(response);
    if (response.status === 200) {
      dispatch({
        type: CATEGORIES_DELETE,
        payload: id,
      });
      dispatch({
        type: COMMON_MESSAGE_SET,
        payload: response.data,
      });
    } else {
      dispatch({
        type: COMMON_ERROR_SET,
        payload: response.message,
      });
    }
  } catch (error) {
    dispatch({
      type: COMMON_ERROR_SET,
      payload: error.response.data
        ? error.response.data.message
        : error.message,
    });
  }
  dispatch({
    type: COMMON_LOADING_SET,
    payload: false,
  });
};

export const getOneCategory = (id) => async (dispatch) => {
  // getCategory
  const service = new CategoryService();
  try {
    console.log("get one category");
    dispatch({
      type: COMMON_LOADING_SET,
      payload: true,
    });
    const response = await service.getOneCategory(id);
    console.log(response);
    if (response.status === 200) {
      dispatch({
        type: CATEGORY_SET,
        payload: response.data,
      });
    } else {
      dispatch({
        type: COMMON_ERROR_SET,
        payload: response.message,
      });
    }
  } catch (error) {
    dispatch({
      type: COMMON_ERROR_SET,
      payload: error.response.data
        ? error.response.data.message
        : error.message,
    });
  }
  dispatch({
    type: COMMON_LOADING_SET,
    payload: false,
  });
};

export const clearCategoryState = () => async (dispatch) => {
  dispatch({
    type: CATEGORIES_STATE_CLEAR,
  });
};

export const clearCategory = () => async (dispatch) => {
  dispatch({
    type: CATEGORY_SET,
    payload: { id: "", name: "", status: "Visible" },
  });
};

export const updateCategory = (id, category, navigate) => async (dispatch) => {
  const service = new CategoryService();
  //   console.log(service);
  try {
    console.log("update Category");
    dispatch({
      type: COMMON_LOADING_SET,
      payload: true,
    });

    const response = await service.updateCategory(id, category);
    console.log(response);
    if (response.status === 200) {
      dispatch({
        type: CATEGORY_SET,
        payload: response.data,
      });
      dispatch({
        type: COMMON_MESSAGE_SET,
        payload: "Category is updated",
      });
    } else {
      dispatch({
        type: COMMON_ERROR_SET,
        payload: response.message,
      });
    }
  } catch (error) {
    console.error("Error: " + error);
    dispatch({
      type: COMMON_ERROR_SET,
      payload: error.response.data
        ? error.response.data.message
        : error.message,
    });
  }
  dispatch({
    type: COMMON_LOADING_SET,
    payload: false,
  });
  navigate("/categories/list");
};
