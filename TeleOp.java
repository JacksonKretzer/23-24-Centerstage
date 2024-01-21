package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "Center stage 23-24")
public class TeleOp extends OpMode {
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backLeft;
    protected DcMotor backRight;
    protected DcMotor linearActuator;
    protected DcMotor linearPan;
    private CRServo intakeLeft;
    private CRServo intakeRight;
    private Servo backDoorLeft;
    private Servo backDoorRight;
    private Servo mainHandLeft;
    private Servo mainHandRight;
    private Servo trussRight;
    private Servo trussLeft;
    private Servo paperLaunch;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        linearActuator = hardwareMap.get(DcMotor.class, "linearActuator");
        linearPan = hardwareMap.get(DcMotor.class, "linearPan");
        intakeLeft = hardwareMap.get(CRServo.class, "intakeLeft");
        intakeRight = hardwareMap.get(CRServo.class, "intakeRight");
        backDoorRight = hardwareMap.get(Servo.class, "backDoorRight");
        backDoorLeft = hardwareMap.get(Servo.class, "backDoorLeft");
        mainHandLeft = hardwareMap.get(Servo.class, "mainHandLeft");
        mainHandRight = hardwareMap.get(Servo.class, "mainHandRight");
        trussRight = hardwareMap.get(Servo.class, "trussRight");
        trussLeft = hardwareMap.get(Servo.class, "trussLeft");
        paperLaunch = hardwareMap.get(Servo.class, "paperLaunch");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y > 0) {
            frontLeft.setPower(1);
            frontRight.setPower(1);
            backLeft.setPower(1);
            backRight.setPower(1);
        }
        if (gamepad1.left_stick_y < 0) {
            frontLeft.setPower(-1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(-1);
        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }

        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double z = gamepad1.right_stick_x;

        double multiplier = 1;
        if (gamepad1.left_trigger > 0.1) {
            multiplier *= 0.55;
        }
        if (gamepad1.right_trigger > 0.1) {
            multiplier *= 0.55;
        }
        double v1 = Range.clip(y - x + z, -multiplier, multiplier);
        double v2 = Range.clip(y + x - z, -multiplier, multiplier);
        double v3 = Range.clip(y + x + z, -multiplier, multiplier);
        double v4 = Range.clip(y - x - z, -multiplier, multiplier);

        frontLeft.setPower(v1);
        frontRight.setPower(v2);
        backLeft.setPower(v3);
        backRight.setPower(v4);
//Linear Actuator Movement
        //Move linear actuator up and down.
        if (gamepad1.dpad_up) {
            // Move linear actuator up (adjust as needed)
            linearActuator.setPower(1.0);
        } else if (gamepad1.dpad_down) {
            // Move linear actuator down (adjust as needed)
            linearActuator.setPower(-1.0);
        } else {
            // Stop linear actuator
            linearActuator.setPower(0.0);
        }
        //Move Linear actuator pan motor using dpad

        if (gamepad1.dpad_left) {
            // Move linear actuator up (adjust as needed)
            linearPan.setPower(1.0);
        } else if (gamepad1.dpad_down) {
            // Move linear actuator down (adjust as needed)
            linearPan.setPower(-1.0);
        } else {
            // Stop linear actuator
            linearPan.setPower(0.0);
        }
//Intake Servos
        while (gamepad2.a) {
            // intake servo in (adjust as needed)
            intakeRight.setPower(1);
        }
        while (gamepad2.x) {
            // intake servo out (adjust as needed)
            intakeRight.setPower(-1.0);


        }
        while (gamepad2.y) {
            // intake servo in (adjust as needed)
            intakeLeft.setPower(-1);
        }
        while(gamepad2.b) {
            // intake servo out (adjust as needed)
            intakeLeft.setPower(1.0);

        }
        intakeLeft.setPower(0);
        intakeRight.setPower(0);

//Drop servos
        if (gamepad2.b) {
            // intake servo in (adjust as needed)
            backDoorRight.setPosition(1.0);
        }
        if (gamepad2.b) {
            // intake servo in (adjust as needed)
            backDoorLeft.setPosition(-1.0);
        }
        //Reset servo position
        if (gamepad2.y) {
            backDoorLeft.setPosition(0.0);
            backDoorLeft.setPosition(0.0);
                   }


//Main hand movement
        //main hand right
        if (gamepad1.a) {
            mainHandRight.setPosition(-1.0);
        } else if (gamepad1.b)
            mainHandRight.setPosition(1.0);
        //main hand right mirror
       if (gamepad1.a) {
            mainHandLeft.setPosition(1.0);
        } else if (gamepad1.b) {
            mainHandLeft.setPosition(0.0);
        }
//Truss movement
        if (gamepad2.right_bumper) {
            trussLeft.setPosition(1.0);
        } else if (gamepad2.left_bumper) {
            trussLeft.setPosition(-1.0);
        }


        if (gamepad2.right_bumper) {
            trussRight.setPosition(-1.0);
        } else if (gamepad2.left_bumper) {
            trussRight.setPosition(1.0);
        }
//Paper airplane launch
        if (gamepad2.dpad_up)
            paperLaunch.setPosition(1.0);}
}









