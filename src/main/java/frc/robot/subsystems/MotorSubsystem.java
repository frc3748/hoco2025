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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;
import java.util.function.DoubleSupplier;

public class MotorSubsystem extends SubsystemBase {
  private final SparkMax motor;
  private final SparkMaxConfig config;
  private final RelativeEncoder encoder;

  public MotorSubsystem() {
    motor = new SparkMax(MotorConstants.kMotorCANID, MotorType.kBrushless);
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

  public Command forwardCommand() {
    return runSpeedCommand(MotorConstants.kForwardSpeed).withName("MotorForward");
  }

  public Command backwardCommand() {
    return runSpeedCommand(-MotorConstants.kBackwardSpeed).withName("MotorBackward");
  }

  public Command runSpeedCommand(double speed) {
    return startEnd(() -> setSpeed(speed), this::stop).withName("MotorRunSpeed");
  }

  public Command runSpeedCommand(DoubleSupplier speed) {
    return run(() -> setSpeed(speed.getAsDouble()))
        .finallyDo(interrupted -> stop())
        .withName("MotorRunSupplier");
  }

  public Command stopCommand() {
    return run(this::stop).withName("MotorStop");
  }

  public Command forwardForCommand(double seconds) {
    return forwardCommand().withTimeout(seconds).withName("MotorForwardTimed");
  }

  public Command backwardForCommand(double seconds) {
    return backwardCommand().withTimeout(seconds).withName("MotorBackwardTimed");
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
