package org.ironmaple.simulation.drivesims.swerve.factories;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import org.ironmaple.simulation.drivesims.swerve.SwerveModuleSimulation;

import java.util.function.Supplier;

/**
 *
 *
 * <h2>Factory builder for {@link SwerveModuleSimulation}</h2>
 *
 * <p>This class provides a factory for the standard swerve module types used in FRC
 *
 * <h3>Purpose</h3>
 *
 * <p>This class provides a simple method of configuring your swerve module simulations.
 *
 * <p>You will provide the type of motors, the gear ratio, and an optional current limit and wheel type.
 *
 */
public class SwerveModuleFactory {
  public enum DRIVE_WHEEL_TYPE {
    RUBBER(1.25),
    TIRE(1.15);

    public final double grippingStrength;


    DRIVE_WHEEL_TYPE(double grippingStrength) {
      this.grippingStrength = grippingStrength;
    }
  }

  private final DCMotor driveMotor;
  private final DCMotor steerMotor;
  private final double driveGearRatio;
  private final double driveCurrentLimitAmps;
  private final DRIVE_WHEEL_TYPE driveWheelType;

  public SwerveModuleFactory(DCMotor driveMotor,
                             DCMotor steerMotor,
                             double driveGearRatio,
                             double driveCurrentLimitAmps,
                             DRIVE_WHEEL_TYPE driveWheelType) {
    this.driveMotor = driveMotor;
    this.steerMotor = steerMotor;
    this.driveGearRatio = driveGearRatio;
    this.driveCurrentLimitAmps = driveCurrentLimitAmps;
    this.driveWheelType = driveWheelType;
  }

  /**
   * creates a <a
   * href="https://www.swervedrivespecialties.com/collections/kits/products/mk4-swerve-module">SDS
   * Mark4 Swerve Module</a> for simulation
   */
  public Supplier<SwerveModuleSimulation> getMark4() {
    return () ->
            new SwerveModuleSimulation(
                    driveMotor,
                    steerMotor,
                    driveCurrentLimitAmps,
                    driveGearRatio,
                    SDSConstants.MK4_DEFAULT_STEER_RATIO,
                    0.2,
                    0.3,
                    driveWheelType.grippingStrength,
                    SDSConstants.WHEEL_DIAMETER_METERS,
                    0.03);
  }

  /**
   * creates a <a
   * href="https://www.swervedrivespecialties.com/collections/kits/products/mk4i-swerve-module">SDS
   * Mark4-i Swerve Module</a> for simulation
   */
  public Supplier<SwerveModuleSimulation> getMark4i() {
    return () ->
            new SwerveModuleSimulation(
                    driveMotor,
                    steerMotor,
                    driveCurrentLimitAmps,
                    driveGearRatio,
                    SDSConstants.MK4I_DEFAULT_STEER_RATIO,
                    0.2,
                    1,
                    driveWheelType.grippingStrength,
                    SDSConstants.WHEEL_DIAMETER_METERS,
                    0.025);
  }

  /**
   * creates a <a href="https://www.swervedrivespecialties.com/products/mk4n-swerve-module">SDS
   * Mark4-n Swerve Module</a> for simulation
   */
  public Supplier<SwerveModuleSimulation> getMark4n() {
    return () ->
            new SwerveModuleSimulation(
                    driveMotor,
                    steerMotor,
                    driveCurrentLimitAmps,
                    driveGearRatio,
                    SDSConstants.MK4N_DEFAULT_STEER_RATIO,
                    0.25,
                    1,
                    driveWheelType.grippingStrength,
                    SDSConstants.WHEEL_DIAMETER_METERS,
                    0.025);
  }

  /**
   * A helper class containing constants for the SDS Mk4 module series.
   */
  public static class SDSConstants {
    public enum Mk4GearRatios {
      L1(8.14),
      L2(6.75),
      L3(6.12),
      L4(5.14);

      public final double ratio;

      Mk4GearRatios(double ratio) {
        this.ratio = ratio;
      }
    }

    public enum Mk4NGearRatios {
      L1(7.13),
      L2(5.9),
      L3(5.36);

      public final double ratio;

      Mk4NGearRatios(double ratio) {
        this.ratio = ratio;
      }
    }

    public static final double MK4_DEFAULT_STEER_RATIO = 12.8;
    public static final double MK4I_DEFAULT_STEER_RATIO = 150.0 / 70.0;
    public static final double MK4N_DEFAULT_STEER_RATIO = 18.75;

    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(2);
  }
}
