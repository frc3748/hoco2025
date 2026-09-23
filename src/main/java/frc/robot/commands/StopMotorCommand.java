package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MotorSubsystem;

public class StopMotorCommand extends Command {
  private final MotorSubsystem motor;

  public StopMotorCommand(MotorSubsystem motor) {
    this.motor = motor;
    addRequirements(motor);
  }

  @Override
  public void execute() {
    motor.stop();
  }
}
