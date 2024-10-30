package frc.robot.subsystems.Intake;



import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.BooleanSupplier;

//these are the device id and digital input from the Roborio and the KrackenX60 which is the motor for the intake.
public class Intake extends SubsystemBase {
    private final TalonFX intakeMotor = new TalonFX(51);
    public final DigitalInput beamBreak = new DigitalInput(0);

  /** Creates a new ExampleSubsystem. */
  public Intake() {
    setDefaultCommand(stopIntake().ignoringDisable(true));
    //intakeMotor.setControl(new StaticBrake());


  }

  /**
   * Example command factory method.
   *
   * @return a command
   */

   //this is to show if the intake has a game object loaded or not.

  private void setVoltage(double voltage) {
    SmartDashboard.putBoolean("beam break", beamBreak.get());
    SmartDashboard.putBoolean("beam broken", beamBroken.getAsBoolean());
    intakeMotor.setVoltage(voltage);
  }

  
  public Command runIntake() {
    return Commands.run(
        () -> setVoltage(-7),

        this
    );
  }


  public Command runEjectintake() {
    return Commands.run(
        () -> setVoltage(2),

        this
    );
  }

  public Command stopIntake(){
    return Commands.run(
      () -> setVoltage(0),
      this
    );
  }


  public Command fastEject(){
    return Commands.run(
      () -> setVoltage(8),
      this
      );
  }

  public Command slowsmartIntake(){
    
    return runIntake().until(beamBroken)
            .andThen(runEjectintake().until(beamNotBroken))
            .andThen(stopIntake());
            
  }



//Smart intake is for the beambreak for when the game object is inserted in to the intake the intake will stop running.-Nic
  public Command smartIntake(){

    return runIntake().until(beamBroken)
            .andThen(runEjectintake().until(beamNotBroken))
            .andThen(stopIntake());

            
  }

    
  
  
  public BooleanSupplier beamBroken = () -> !beamBreak.get();
  public BooleanSupplier beamNotBroken = () -> beamBreak.get();


  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}


