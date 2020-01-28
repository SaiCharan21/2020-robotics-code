package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.*;


public class Intake extends SubsystemBase 
{
    //add static field for count of balls. 
    private SpeedController intake;
    private int amtBallsHeld = 0;    

    public Intake(SpeedController intake) 
    {
         this.intake = intake;
    }
    public void moveIntake(double Intake)
    {
        intake.set(Intake);
    }
    public void stop()
    {
       intake.stopMotor();
    }

    //add another method to get proximity input (true or false).


    public void increaseBalls()
    {
        //if proximity in certain range, then increase ball count
    }
}