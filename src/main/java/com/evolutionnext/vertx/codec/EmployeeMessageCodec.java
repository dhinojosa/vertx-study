package com.evolutionnext.vertx.codec;

import com.evolutionnext.vertx.pojo.Employee;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class EmployeeMessageCodec implements MessageCodec<Employee, Employee> {

    @Override
    public void encodeToWire(Buffer buffer, Employee employee) {
        buffer.appendInt(employee.getFirstName().getBytes().length)
              .appendString(employee.getFirstName())
              .appendInt(employee.getLastName().getBytes().length)
              .appendString(employee.getLastName());
    }

    @Override
    public Employee decodeFromWire(int pos, Buffer buffer) {
        int firstNameLength = buffer.getInt(0);
        String firstName = buffer.getString(4, 4 + firstNameLength);
        int lastNameLength = buffer.getInt(4 + firstNameLength);
        int lastNameStart = 8 + firstName.getBytes().length;
        String lastName = buffer.getString(lastNameStart, lastNameStart + lastNameLength);
        return new Employee(firstName, lastName);
    }

    @Override
    public Employee transform(Employee employee) {
        return employee;
    }

    @Override
    public String name() {
        return "com.evolutionnext.vertx.Employee";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
