package frc.robot.Systems;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Module extends SubsystemBase {
    TalonSRX talon;

    public Module(int ID) {
        talon = new TalonSRX(ID);
    }

    // public int setCurrentLimit(int limit) {
    // }
}
