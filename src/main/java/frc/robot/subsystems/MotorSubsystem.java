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

/**
 * A single brushless motor on a SparkMax. Hardware only -- the behavior lives in the commands under
 * frc.robot.commands.
 */
public class MotorSubsystem extends SubsystemBase {
  private final SparkMax m_motor;
  private final SparkMaxConfig m_config;
  private final RelativeEncoder m_encoder;

  public MotorSubsystem() {
    m_motor = new SparkMax(MotorConstants.kMotorCANID, MotorType.kBrushless);
    m_config = new SparkMaxConfig();
    m_encoder = m_motor.getEncoder();
    m_encoder.setPosition(0);

    // Inversion goes through the config, not setInverted() -- that is deprecated
    // in REVLib 2025.
    m_config
        .inverted(MotorConstants.kInverted)
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(MotorConstants.kCurrentLimit);

    m_motor.configure(m_config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_motor.clearFaults();

    setName("Motor");
  }

  /**
   * Runs the motor at the given duty cycle.
   *
   * @param speed output in [-1, 1]; positive is forward.
   */
  public void setSpeed(double speed) {
    if (!Double.isFinite(speed)) {
      stop();
      return;
    }
    m_motor.set(MathUtil.clamp(speed, -1.0, 1.0));
  }

  public void stop() {
    m_motor.stopMotor();
  }

  public double getSpeed() {
    return m_motor.get();
  }

  /** Motor rotations since startup (before any gearing). */
  public double getPosition() {
    return m_encoder.getPosition();
  }

  /** Motor RPM. */
  public double getVelocity() {
    return m_encoder.getVelocity();
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("GeneralSubsystem");
    builder.addDoubleProperty("Output", this::getSpeed, null);
    builder.addDoubleProperty("Position", this::getPosition, null);
    builder.addDoubleProperty("Velocity", this::getVelocity, null);
    builder.addDoubleProperty("Current", m_motor::getOutputCurrent, null);
    builder.addDoubleProperty("Temperature", m_motor::getMotorTemperature, null);
  }
}
