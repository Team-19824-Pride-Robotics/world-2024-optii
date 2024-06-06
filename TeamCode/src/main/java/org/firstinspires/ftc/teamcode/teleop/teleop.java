package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;


@Config
@TeleOp(name = "teleop", group = "teleop")
public class teleop extends LinearOpMode {


    public static double sv;
    public static double ds = 0;


    DcMotorEx D1;
    ServoImplEx servo;
    AnalogInput sEncoder;


    public void runOpMode() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        D1 = hardwareMap.get(DcMotorEx.class, "D1");
        servo = (ServoImplEx) hardwareMap.get(Servo.class, "S1");
        servo.setPwmRange(new PwmControl.PwmRange(505, 2495));
        sEncoder = hardwareMap.get(AnalogInput.class, "sEncoder");

        waitForStart();

        while (opModeIsActive()) {





            double pos = sEncoder.getVoltage() / 3.3 * 360;
            double cpos = 352.040816/pos;
            ds = gamepad1.right_stick_y;
            sv= (gamepad1.left_stick_x+1)/2;
            D1.setPower(ds);

            sv = Range.clip(sv, .01, .99);

            servo.setPosition(sv);

            telemetry.addData("Run time",getRuntime());
            telemetry.addData("pos1", pos);
            telemetry.update();
        }

    }

    //when G2y changes states from what it previously was



}