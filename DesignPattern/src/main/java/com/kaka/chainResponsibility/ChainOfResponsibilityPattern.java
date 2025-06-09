package com.kaka.chainResponsibility;

import java.util.logging.Handler;

public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        LeaveHandler groupLeader = new GroupLeaderHandler();
        LeaveHandler manager = new ManagerHandler();
        LeaveHandler generalManager = new GeneralManagerHandler();

        // 构建责任链
        groupLeader.setNextHandler(manager);
        manager.setNextHandler(generalManager);

        // 提交请假请求
        LeaveRequest request1 = new LeaveRequest(2);
        groupLeader.handleRequest(request1);  // 组长处理

        LeaveRequest request2 = new LeaveRequest(5);
        groupLeader.handleRequest(request2);  // 经理处理

        LeaveRequest request3 = new LeaveRequest(10);
        groupLeader.handleRequest(request3); // 总经理处理
    }


    public static class LeaveRequest {
        private int days;

        public LeaveRequest(int days) {
            this.days = days;
        }

        public int getDays() {
            return days;
        }
    }

    public static abstract class LeaveHandler {
        protected LeaveHandler nextHandler;

        public void setNextHandler(LeaveHandler next) {
            this.nextHandler = next;
        }

        public abstract void handleRequest(LeaveRequest request);
    }

    // 组长处理者
    public static class GroupLeaderHandler extends LeaveHandler {
        @Override
        public void handleRequest(LeaveRequest request) {
            if (request.getDays() <= 3) {
                System.out.println("组长批准请假" + request.getDays() + "天");
            } else if (nextHandler != null) {
                nextHandler.handleRequest(request); // 传递给经理
            }
        }
    }

    // 经理处理者
    public static class ManagerHandler extends LeaveHandler {
        @Override
        public void handleRequest(LeaveRequest request) {
            if (request.getDays() <= 7) {
                System.out.println("经理批准请假" + request.getDays() + "天");
            } else if (nextHandler != null) {
                nextHandler.handleRequest(request); // 传递给总经理
            }
        }
    }

    // 总经理处理者
    public static class GeneralManagerHandler extends LeaveHandler {
        @Override
        public void handleRequest(LeaveRequest request) {
            if (request.getDays() > 7) {
                System.out.println("总经理批准请假" + request.getDays() + "天");
            } else {
                System.out.println("请假天数过长，无法批准");
            }
        }
    }

}
