// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.MotorConstants;
import frc.robot.subsystems.MotorSubsystem;

public class MoveMotor extends Command {
  private final MotorSubsystem motor;
  private final double speed;
  private final double timeoutSeconds;
  private final Timer timer = new Timer();

  public MoveMotor(MotorSubsystem motor, double speed) {
    this(motor, speed, Double.POSITIVE_INFINITY);
  }

  public MoveMotor(MotorSubsystem motor, double speed, double timeoutSeconds) {
    this.motor = motor;
    this.speed = speed;
    this.timeoutSeconds = timeoutSeconds;
    addRequirements(motor);
  }

  public static MoveMotor forward(MotorSubsystem motor) {
    return new MoveMotor(motor, MotorConstants.kForwardSpeed);
  }

  public static MoveMotor backward(MotorSubsystem motor) {
    return new MoveMotor(motor, -MotorConstants.kBackwardSpeed);
  }

  public static MoveMotor forwardFor(MotorSubsystem motor, double seconds) {
    return new MoveMotor(motor, MotorConstants.kForwardSpeed, seconds);
  }

  public static MoveMotor backwardFor(MotorSubsystem motor, double seconds) {
    return new MoveMotor(motor, -MotorConstants.kBackwardSpeed, seconds);
  }

  @Override
  public void initialize() {
    timer.restart();
  }

  @Override
  public void execute() {
    motor.setSpeed(speed);
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(timeoutSeconds);
  }

  @Override
  public void end(boolean interrupted) {
    motor.stop();
  }
}
