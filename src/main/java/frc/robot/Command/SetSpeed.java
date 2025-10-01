package frc.robot.Command;
 
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.Systems.*;
import edu.wpi.first.wpilibj2.command.Command;

public class SetSpeed extends Command {
    Drive drive;
    CommandXboxController xboxCtrl;

    public SetSpeed(Drive drive, CommandXboxController xboxCtrl) {
        this.drive = drive;
        this.xboxCtrl = xboxCtrl;

        addRequirements(drive);
    }

    @Override
    public void execute() {
        double speed = xboxCtrl.getRawAxis(1) * -Constants.speedMultiplier;
        double turn = xboxCtrl.getRawAxis(4) * Constants.speedMultiplier;

        double left = speed + turn;
        double right = speed - turn;

        drive.runDrive(left, right);
    }
}
