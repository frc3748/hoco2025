package frc.robot.Command;
 
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Systems.*;
import edu.wpi.first.wpilibj2.command.Command;

public class SetSpeed extends Command {
    Drive drive;
    XboxController xboxCtrl;

    public SetSpeed(Drive drive, XboxController xboxCtrl) {
        this.drive = drive;
        this.xboxCtrl = xboxCtrl;

        addRequirements(drive);
    }

    @Override
    public void execute() {
        double xSpeed = xboxCtrl.getLeftX();
        double ySpeed = xboxCtrl.getLeftY();

        drive.runDrive(xSpeed, ySpeed);
    }
}
