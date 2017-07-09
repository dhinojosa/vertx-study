package com.evolutionnext.vertx.codec;

import com.evolutionnext.vertx.pojo.Employee;
import io.vertx.core.buffer.Buffer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeMessageCodecTest {

    @Test
    public void testEncodeAndDecode() throws Exception {
        EmployeeMessageCodec employeeMessageCodec = new EmployeeMessageCodec();
        Buffer buffer = Buffer.buffer();
        employeeMessageCodec.encodeToWire(buffer, new Employee("Nicolas", "Cage"));

        System.out.println(buffer);
        Employee employee = employeeMessageCodec.decodeFromWire(0, buffer);

        assertEquals("Nicolas" , employee.getFirstName());
        assertEquals("Cage" , employee.getLastName());
    }
}