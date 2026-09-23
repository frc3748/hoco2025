// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.MoveMotorCommand;
import frc.robot.commands.StopMotorCommand;
import frc.robot.subsystems.MotorSubsystem;

public class RobotContainer {
  private final MotorSubsystem motor = new MotorSubsystem();

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();

    SmartDashboard.putData(motor);
  }

  private void configureBindings() {
    motor.setDefaultCommand(new StopMotorCommand(motor));

    m_driverController.a().whileTrue(MoveMotorCommand.forward(motor));
    m_driverController.b().whileTrue(MoveMotorCommand.backward(motor));
  }
}
