package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveShooter extends CommandBase
{
    private int loops = 0;
    private double velocity = 0;

    public MoveShooter()
    {
        addRequirements(RobotContainer.getShooter(), RobotContainer.getPulley());
    }

    @Override
    public void execute() {
        double motorOutput = RobotContainer.getShooter().getMotorOutputPercent();
        RobotContainer.getBuilder().append("\tout:");
        RobotContainer.getBuilder().append(motorOutput);
        RobotContainer.getBuilder().append(("\tspd:"));
        RobotContainer.getBuilder().append(RobotContainer.getShooter().getVelocity());
        SmartDashboard.putNumber("Velocity", RobotContainer.getShooter().getVelocity());

        double targetVelocity = 32;

        RobotContainer.getShooterMotor().set(ControlMode.Velocity, targetVelocity*((double)4096/(double)600));

        RobotContainer.getBuilder().append("\terr:");
        RobotContainer.getBuilder().append(RobotContainer.getShooterMotor().getClosedLoopError(Constants.SHOOTER_PID_ID)*((double)600/(double)4096));
        RobotContainer.getBuilder().append(("\ttrg:"));
        RobotContainer.getBuilder().append(targetVelocity);

        if(RobotContainer.getShooterMotor().getClosedLoopError(Constants.SHOOTER_PID_ID)*((double)600/(double)4096) <= Constants.SHOOTER_ERROR_TOLERANCE)
        {
            RobotContainer.loop++;
        }
        else
        {
            RobotContainer.loop = 0;
        }

        if(RobotContainer.loop >= 2)
        {
            RobotContainer.getPulley().movePulley(Constants.PULLEY_TELEOP_SPEED);
            RobotContainer.loop = 0;
        }

        System.out.println(RobotContainer.getBuilder().toString());
        RobotContainer.getBuilder().setLength(0);
    }
}