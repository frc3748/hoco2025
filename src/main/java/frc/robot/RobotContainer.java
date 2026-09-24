// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  private final MotorSubsystem motor = new MotorSubsystem();

  private final CommandXboxController m_driverController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();

    SmartDashboard.putData(motor);
  }

  private void configureBindings() {
    motor.setDefaultCommand(new StopMotor(motor));

    m_driverController.a().whileTrue(MoveMotor.forward(motor));
    m_driverController.b().whileTrue(MoveMotor.backward(motor));
  }
}
