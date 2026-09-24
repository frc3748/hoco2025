// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MotorSubsystem extends SubsystemBase {
  private final SparkMax motor;
  private final SparkMaxConfig config;
  private final RelativeEncoder encoder;

  public MotorSubsystem() {
    this(MotorConstants.kMotorCANID);
  }

  public MotorSubsystem(int CANID) {
    motor = new SparkMax(CANID, MotorType.kBrushless);
    config = new SparkMaxConfig();
    encoder = motor.getEncoder();
    encoder.setPosition(0);

    config
        .inverted(MotorConstants.kInverted)
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(MotorConstants.kCurrentLimit);

    motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motor.clearFaults();

    setName("Motor");
  }

  public void setSpeed(double speed) {
    if (!Double.isFinite(speed)) {
      stop();
      return;
    }
    motor.set(MathUtil.clamp(speed, -1.0, 1.0));
  }

  public void stop() {
    motor.stopMotor();
  }

  public double getSpeed() {
    return motor.get();
  }

  public double getPosition() {
    return encoder.getPosition();
  }

  public double getVelocity() {
    return encoder.getVelocity();
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("GeneralSubsystem");
    builder.addDoubleProperty("Output", this::getSpeed, null);
    builder.addDoubleProperty("Position", this::getPosition, null);
    builder.addDoubleProperty("Velocity", this::getVelocity, null);
    builder.addDoubleProperty("Current", motor::getOutputCurrent, null);
    builder.addDoubleProperty("Temperature", motor::getMotorTemperature, null);
  }
}
