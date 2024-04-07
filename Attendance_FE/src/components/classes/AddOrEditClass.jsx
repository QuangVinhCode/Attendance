import React, { Component } from "react";
import withRouter from "../../helpers/withRouter";
import { Col, Divider, Row, Form, Input, Button, Popconfirm, Select } from "antd";
import ContentHeader from "../common/ContentHeader";
import {
  insertClass,
  getClass,
  clearClass,
  updateClass,
} from "../../redux/actions/classAction";
import { getTeachers } from "../../redux/actions/teacherAction";
import { connect } from "react-redux";
const { Option } = Select;
class AddOrEditClass extends Component {
  formRef = React.createRef();
  constructor(props) {
    super(props);

    this.state = {
      object: { id: "", className: "", teacher_id: "" },
    };
  }
  componentDidMount = () => {
    this.props.getTeachers();
    if (this.props.router.params.id) {
      this.props.getClass(this.props.router.params.id);
    } else {
      this.props.clearClass();
    }
  };

  static getDerivedStateFromProps(nextProps, prevState) {
    // Kiểm tra xem nextProps có object không và object có thay đổi không
    if (nextProps.object && prevState.object.id !== nextProps.object.id) {
      // Nếu có thay đổi, cập nhật state với dữ liệu từ nextProps.object
      return {
        ...prevState,
        object: nextProps.object,
      };
    } else if (!nextProps.object) {
      // Nếu không có nextProps.object, reset state với giá trị mặc định
      return {
        ...prevState,
        object: { id: "", className: "", teacher_id: "" },
      };
    }
    // Không cần cập nhật state
    return null;
  }
  confirmUpdate = () => {
    console.log("Cập nhật  lớp");
    this.formRef.current.submit();
  };

  onSubmitForm = (values) => {
    console.log(values);

    const { navigate } = this.props.router;
    const { id } = this.state.object;
    //this.state.object.id
    if (!id) {
      this.props.insertClass(values, navigate);
    } else {
      this.props.updateClass(id, values, navigate);
    }
  };

  render() {
    const { navigate } = this.props.router;
    const { isLoading } = this.props;
    const { object } = this.state;
    const { teachers } = this.props;
    console.log("oject in this.state:");
    console.log(object);
    let title = "Thêm lớp mới";
    if (object.id) {
      title = "Cập nhật lớp";
    }
    return (
      <div>
        <ContentHeader
          navigate={navigate}
          title={title}
          className="site-page-header"
        ></ContentHeader>

        <Form
          layout="vertical"
          className="form"
          onFinish={this.onSubmitForm}
          key={object.id}
          ref={this.formRef}
        >
          <Row>
            <Col md={12}>
              <Form.Item
                label="Mã lớp"
                name="class_id"
                initialValue={object.id}
              >
                <Input readOnly></Input>
              </Form.Item>
              <Form.Item
                label="Tên lớp"
                name="className"
                initialValue={object.className}
                rules={[{ required: true, min: 2 }]}
              >
                <Input></Input>
              </Form.Item>
              <Form.Item
                label="Giáo viên"
                name="teacher_id"
                initialValue={object.teacher ? object.teacher.id : undefined}
                rules={[{ required: true, message: "Làm ơn chọn giáo viên" }]}
              >
                <Select placeholder="Chọn giáo viên">
                  {teachers.map((teacher) => (
                    <Option key={teacher.id} value={teacher.id}>
                      {teacher.name}
                    </Option>
                  ))}
                </Select>
              </Form.Item>
              <Divider></Divider>
              {!object.id && (
                <Button
                  htmlType="submit"
                  type="primary"
                  style={{ float: "right" }}
                  loading={isLoading}
                >
                  Thêm mới
                </Button>
              )}
              {object.id && (
                <Popconfirm
                  title="Bạn muốn cập nhật không ?"
                  onConfirm={this.confirmUpdate}
                  okText="Đồng ý"
                  cancelText="Hủy"
                >
                  <Button
                    htmlType="submit"
                    type="primary"
                    style={{ float: "right" }}
                    loading={isLoading}
                  >
                    Cập nhật
                  </Button>
                </Popconfirm>
              )}
            </Col>
          </Row>
        </Form>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  object: state.classReducer.object,
  teachers: state.teacherReducer.objects,
  isLoading: state.commonReducer.isLoading,
});

const mapDispatchToProps = {
  insertClass,
  getClass,
  clearClass,
  updateClass,
  getTeachers,
};

export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(AddOrEditClass)
);
