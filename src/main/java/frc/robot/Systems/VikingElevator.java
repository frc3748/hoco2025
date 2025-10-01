package frc.robot.Systems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VikingElevator extends SubsystemBase {
    Module module;

    public VikingElevator(int ID) {
       module = new Module(ID);

    //    dr
    }

    public void moveMotor(double speed) {
        module.talon.set(TalonSRXControlMode.PercentOutput, speed);
    }
}
