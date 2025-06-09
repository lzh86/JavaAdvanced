package com.kaka.chainResponsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainOfResponsibilityPattern2 {
    public static void main(String[] args) {
        double d = .09d;
        System.out.println(d);
        UnaryOperator<LeaveRequest> groupLeader = request -> {
            if (request.getDays() <= 3) {
                System.out.println("组长批准请假" + request.getDays() + "天");
                return request; // 已处理，不再传递
            } else {
                System.out.println("组长无法处理，请假超过3天，转交经理");
                return request; // 返回原请求，继续传递
            }
        };

        UnaryOperator<LeaveRequest> manager = request -> {
            if (request.getDays() <= 7) {
                System.out.println("经理批准请假" + request.getDays() + "天");
                return request;
            } else {
                System.out.println("经理无法处理，请假超过7天，转交总经理");
                return request;
            }
        };

        UnaryOperator<LeaveRequest> generalManager = request -> {
            System.out.println("总经理批准请假" + request.getDays() + "天");
            return request;
        };

        // 构建处理链
        Function<LeaveRequest, LeaveRequest> function = groupLeader.andThen(manager).andThen(generalManager);

        // 提交请假请求
        LeaveRequest request1 = new LeaveRequest(2);
        function.apply(request1);

        LeaveRequest request2 = new LeaveRequest(5);
        function.apply(request2);

        LeaveRequest request3 = new LeaveRequest(10);
        function.apply(request3);


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
}
