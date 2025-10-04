package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Systems.VikingPID;

public class MovePID extends Command {
    VikingPID vikingPID;
    double angle;

    public MovePID(VikingPID vikingPID, double angle) {
        this.vikingPID = vikingPID;
        this.angle = angle;

        addRequirements(vikingPID);
    }

    @Override
    public void execute() {
        vikingPID.MoveMotorPID(angle);
    }

    @Override
    public void end(boolean interrupted) {
        vikingPID.SetPower(0);
    }
}
