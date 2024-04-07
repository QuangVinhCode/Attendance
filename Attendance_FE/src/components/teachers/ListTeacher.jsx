import React, { Component } from "react";
import withRouter from "../../helpers/withRouter";
import ContentHeader from "../common/ContentHeader";
import { Button, Space, Table, Modal, Skeleton } from "antd";
import Column from "antd/lib/table/Column";
import {
  DeleteOutlined,
  ExclamationCircleOutlined,
} from "@ant-design/icons";
import { connect } from "react-redux";
import { getTeachers } from "../../redux/actions/teacherAction";

class ListTeacher extends Component {
  constructor() {
    super();

    this.state = {
      object: {},
    };
  }
  componentDidMount = () => {
    this.props.getTeachers();

    console.log("Did Mount");
  };

  componentWillUnmount = () => {
  //  this.props.clearClassState();
    console.log("Will Unmount");
  };


  deleteClass = () => {
    this.props.deleteClass(this.state.object.id);
  };

  openDeleteConfirmModal = (object) => {
    this.setState({ ...this.state, object: object });
    console.log(object);
    const message = "Bạn có chắt chắn muốn xóa giáo viên " + object.name;

    Modal.confirm({
      title: "Xác nhận",
      icon: <ExclamationCircleOutlined />,
      content: message,
      onOk: this.deleteClass,
      okText: "Xóa",
      cancelText: "Hủy",
    });
  };

  render() {
    const { navigate } = this.props.router;
    const { objects, isLoading } = this.props;
    if (isLoading) {
      return (
        <>
          <ContentHeader
            navigate={navigate}
            title="Danh sách giáo viên"
            className="site-page-header"
          ></ContentHeader>
          <Skeleton active />
        </>
      );
    }
    return (
      <>
        <ContentHeader
          navigate={navigate}
          title="Danh sách giáo viên"
          className="site-page-header"
        ></ContentHeader>
        <Table dataSource={objects} size="small" rowKey="id">
          <Column
            title="Mã lớp"
            key="id"
            dataIndex="id"
            width={40}
            align="center"
          ></Column>
          <Column
            title="Tên giáo viên"
            key="name"
            dataIndex="name"
            width={80}
            align="center"
          ></Column>
          <Column
            title="Tác vụ"
            key="action"
            dataIndex="action"
            width={140}
            align="center"
            render={(_, record) => (
              <Space size="middle">
                <Button
                  key={record.key}
                  type="primary"
                  danger
                  size="small"
                  onClick={() => this.openDeleteConfirmModal(record)}
                >
                  <DeleteOutlined style={{ marginRight: 8 }} />
                  Xóa
                </Button>
              </Space>
            )}
          ></Column>
        </Table>
      </>
    );
  }
}

const mapStateToProps = (state) => ({
  objects: state.teacherReducer.objects,
  isLoading: state.commonReducer.isLoading,
});

const mapDispatchToProps = {
  getTeachers,
};

export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(ListTeacher)
);
