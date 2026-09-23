// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  private Constants() {}

  public static final class MotorConstants {
    private MotorConstants() {}

    public static final int kMotorCANID = 10; // CHANGE

    public static final boolean kInverted = false;

    public static final int kCurrentLimit = 30;

    public static final double kForwardSpeed = 0.4;
    public static final double kBackwardSpeed = 0.4;

    public static final double kManualSpeed = 0.6;

    public static final double kDeadband = 0.1;

    public static final double kTimedMoveSeconds = 1.0;
  }

  public static final class OperatorConstants {
    private OperatorConstants() {}

    public static final int kDriverControllerPort = 0;
  }
}
