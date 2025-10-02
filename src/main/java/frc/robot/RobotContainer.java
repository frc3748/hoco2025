package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Command.Move;
import frc.robot.Command.MovePID;
import frc.robot.Command.SetSpeed;
import frc.robot.Systems.*;

public class RobotContainer {
  Constants constants = new Constants();
  Drive drive = new Drive(constants.xID1, constants.xID2, constants.yID1, constants.yID2);
  CommandXboxController xboxCtrl = new CommandXboxController(0);
  SetSpeed setSpeed = new SetSpeed(drive, xboxCtrl);

  VikingElevator vikingEl = new VikingElevator(13);
  Move move = new Move(vikingEl);

  VikingPID vikingPID = new VikingPID(17);
  MovePID movePID = new MovePID(vikingPID, 20);

  public RobotContainer() {
    configureBindings();
  }
  

  private void configureBindings() {
    drive.setDefaultCommand(setSpeed);
    
    xboxCtrl.a().onTrue(movePID);
    xboxCtrl.x().whileTrue(move);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}