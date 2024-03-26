import React, { Component } from "react";
import withRouter from "../../helpers/withRouter";
import { Button, Col, Divider, Form, Input, Row, Select } from "antd";
import ContentHeader from "../common/ContentHeader";
import {
  clearCategory,
  getOneCategory,
  insertCategory,
  updateCategory,
} from "./../../redux/actions/categoryActions";
import { connect } from "react-redux";
class AddOrEditCategory extends Component {
  constructor(props) {
    super(props);

    this.state = {
      category: {
        id: "",
        name: "",
        status: "Visible",
      },
    };
  }

  componentDidMount = () => {
    const { id } = this.props.router.params;
    if (id) {
      this.props.getOneCategory(id);
    } else {
      this.props.clearCategory();
    }
  };

  static getDerivedStateFromProps(nextProps, prevState) {
    if (nextProps.category && prevState.category.id !== nextProps.category.id) {
      return {
        ...prevState,
        category: nextProps.category,
      };
    } else if (!nextProps.category) {
      return {
        ...prevState,
        category: {
          id: "",
          name: "",
          status: "Visible",
        },
      };
    }
    return null;
  }
  onSubmitForm = (values) => {
    console.log(values);
    const { navigate } = this.props.router;
    const { id } = this.state.category;
    if (!this.state.category.id) {
      this.props.insertCategory(values, navigate);
    } else {
      this.props.updateCategory(id, values, navigate);
    }
  };

  render() {
    const { navigate } = this.props.router;
    const { isLoading } = this.props;
    const { category } = this.state;
    return (
      <div>
        <ContentHeader
          navigate={navigate}
          title="Add new category"
          className="site-page-header"
        />
        <Form
          layout="vertical"
          className="form"
          onFinish={this.onSubmitForm}
          key={category.id}
          disabled={isLoading}
        >
          <Row>
            <Col md={12}>
              <Form.Item
                label="Category ID"
                name="categoryId"
                initialValue={category.id}
                hidden={category.id ? false : true}
              >
                <Input readOnly></Input>
              </Form.Item>
              <Form.Item
                label="Name"
                name="name"
                initialValue={category.name}
                rules={[{ required: true, min: 2, message: "Please input!" }]}
              >
                <Input></Input>
              </Form.Item>
              <Form.Item
                label="Status"
                name="status"
                initialValue={category.status === "Visible" ? "0" : "1"}
              >
                <Select>
                  <Select.Option value="0">Visible</Select.Option>
                  <Select.Option value="1">In-Visible</Select.Option>
                </Select>
              </Form.Item>
              <Divider></Divider>
              <Button
                htmlType="submit"
                type="primary"
                style={{ float: "right" }}
                loading={isLoading}
              >
                Save
              </Button>
            </Col>
          </Row>
        </Form>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  category: state.categoryReducer.category,
  isLoading: state.commonReducer.isLoading,
});

const mapDispatchToProps = {
  insertCategory,
  getOneCategory,
  clearCategory,
  updateCategory,
};

export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(AddOrEditCategory)
);
