package frc.robot.Systems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VikingPID extends SubsystemBase {
    SparkMax spark;
    SparkMaxConfig sparkConfig;
    SparkClosedLoopController sparkPID;
    RelativeEncoder relativeEncoder;

    PIDController pid;

    public VikingPID(int SparkCANID) {
        spark = new SparkMax(SparkCANID, MotorType.kBrushless);
        sparkConfig = new SparkMaxConfig();
        sparkPID = spark.getClosedLoopController();
        
        relativeEncoder = spark.getEncoder();

        sparkConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake)
            .smartCurrentLimit(30);
        sparkConfig.encoder
            .positionConversionFactor(360);
        sparkConfig.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(0.001, 0, 0)
            .outputRange(-0.1, 0.1)
            .positionWrappingInputRange(0,360)
            .positionWrappingEnabled(true);

        spark.configure(sparkConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void MoveMotorPID(double degrees) {
        sparkPID.setReference(degrees, ControlType.kPosition);
    }

    public void SetPower(double power) {
        spark.set(power);
    }
}