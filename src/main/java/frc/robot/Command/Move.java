package frc.robot.Command;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Systems.VikingElevator;

public class Move extends Command {
    VikingElevator vikingEl;
    long startTime;

    boolean toggled = false;

    public Move(VikingElevator vikingEl) {
        this.vikingEl = vikingEl;

        addRequirements(vikingEl);

        startTime = 0;
    }

    @Override
    public void execute() {
        if (System.currentTimeMillis() - startTime > (Constants.threshold * 1000)) {
            if (toggled) {
                vikingEl.moveMotor(Constants.moveFactor);
                toggled = false;
            }
            else {
                vikingEl.moveMotor(-Constants.moveFactor);
                toggled = true;
            }

            startTime = System.currentTimeMillis();
        }
    }

    @Override
    public void end(boolean interrupted) {
        vikingEl.moveMotor(0);
    }
}
