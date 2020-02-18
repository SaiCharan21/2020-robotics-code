
// Put methods for controlling this subsystem
// here. Call these from Commands.
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveWithJoystick;

public class DriveTrain extends SubsystemBase
{
    private SpeedControllerGroup left, right;

    private DifferentialDrive driveBase;

    public DriveTrain(SpeedControllerGroup left, SpeedControllerGroup right, DifferentialDrive driveBase)
    {
        this.left = left;
        this.right = right;
        this.driveBase = driveBase;
        driveBase.setRightSideInverted(false);
    }

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        left.set(leftSpeed);
        right.set(-rightSpeed);
        System.out.println("Speed: " + left.get() + "::" + right.get());
        //2019 robot
    }

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();
        System.out.println("******************************");
    }

    public void takeJoystickInputs(Joystick joy)
    {
        System.out.println(left.get()+ "::"+ right.get());
        driveBase.arcadeDrive(joy.getY(), -joy.getZ()* Constants.Z_ROT_DAMPENING);
    }

    public double getAvgDistance()
    {
        return (RobotContainer.getEncLeft().getDistance() + RobotContainer.getEncRight().getDistance()) / 2;
    }

    public double getAvgRate()
    {
        return (left.get()+right.get())/2;
    }

    @Override
    public void periodic()
    {

    }
    
}
