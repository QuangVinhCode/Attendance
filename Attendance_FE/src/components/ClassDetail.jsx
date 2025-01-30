import React, { Component } from "react";

import { Modal, Table } from "antd";

import { connect } from "react-redux";

import Column from "antd/lib/table/Column";
import { getAttendanceInfoByStudentAndClass } from "../redux/actions/attendanceAction";
import withRouter from "../helpers/withRouter";
import AuthService from "../services/auth.service";
class ClassDetail extends Component {
  constructor(props) {
    super(props);
    this.state = {
      reports: {},
    };
  }

  componentDidMount() {
    const userInfo = AuthService.getUserInfo();
    const object = {
      id_Class: 1,
      id_Student: "DH517320240829140904",
    };
    this.props.getAttendanceInfoByStudentAndClass(this.props.id,userInfo.username);
  }

  componentWillUnmount = () => {
    //  this.props.clearReportDetailsState();
  };

  render() {
    const { onCancel, open } = this.props;

    const { attendances } = this.props;
    return (
      <Modal
        title="Chi tiết báo cáo tài liệu"
        visible={open}
        onCancel={onCancel}
        cancelText="Đóng"
        okButtonProps={{ style: { display: "none" } }}
        width="100%"
        style={{ maxHeight: "80vh", overflowY: "auto" }}
      >
        <Table dataSource={attendances} size="small" rowKey="id">
          <Column
            title="id"
            key="id"
            dataIndex="id"
            width={40}
            align="center"
          />
          <Column
            title="Name"
            key="name"
            dataIndex="name"
            width={40}
            align="center"
          />
          <Column
            title="Status"
            key="status"
            dataIndex="status"
            width={40}
            align="center"
          />
        </Table>
      </Modal>
    );
  }
}

const mapStateToProps = (state) => ({
  attendances: state.attendanceReducer.attendances,
});

const mapDispatchToProps = {
  getAttendanceInfoByStudentAndClass,
};

export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(ClassDetail)
);
