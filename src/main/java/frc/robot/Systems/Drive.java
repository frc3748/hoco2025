package frc.robot.Systems;

import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    ArrayList<Module> xModules = new ArrayList<>();
    
    ArrayList<Module> yModules = new ArrayList<>();

    Constants constants = new Constants();

    public Drive(int x1, int x2, int y1, int y2) {
        xModules.add(new Module(x1));
        xModules.add(new Module(x2));

        xModules.get(1).talon.set(ControlMode.Follower, x1);

        yModules.add(new Module(y1));
        yModules.add(new Module(y2));

        yModules.get(1).talon.set(ControlMode.Follower, y1);

        setCurrentLimit(constants.currentLimit);
    }

    public void setCurrentLimit(int currentLimit) {
        for (int i = 0; i < xModules.size(); i++) {
            xModules.get(i).talon.configContinuousCurrentLimit(currentLimit);
            xModules.get(i).talon.configPeakCurrentLimit(currentLimit);

            yModules.get(i).talon.configContinuousCurrentLimit(currentLimit);
            yModules.get(i).talon.configPeakCurrentLimit(currentLimit);
        }
    }

    public void runDrive(double x, double y) {
        xModules.get(0).talon.set(ControlMode.PercentOutput, x);
        yModules.get(0).talon.set(ControlMode.PercentOutput, -y);
    }
}
