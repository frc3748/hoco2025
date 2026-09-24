// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  private final Constants constants = new Constants();

  private final Drive drive =
      new Drive(constants.xID1, constants.xID2, constants.yID1, constants.yID2);

  private final MotorSubsystem motor = new MotorSubsystem();

  private final CommandXboxController xboxCtrl = new CommandXboxController(0);

  private final SetSpeed setSpeed = new SetSpeed(drive, xboxCtrl);

  public RobotContainer() {
    configureBindings();

    SmartDashboard.putData(motor);
  }

  private void configureBindings() {
    drive.setDefaultCommand(setSpeed);

    motor.setDefaultCommand(new StopMotor(motor));

    xboxCtrl.a().whileTrue(MoveMotor.forward(motor));
    xboxCtrl.b().whileTrue(MoveMotor.backward(motor));
  }
}
