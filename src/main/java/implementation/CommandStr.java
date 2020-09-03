package implementation;

public class CommandStr {
    public String cmd;
    public String args;

    public CommandStr(String cmd, String args) {
        this.cmd = cmd;
        this.args = args;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "CommandStr{" +
                "cmd='" + cmd + '\'' +
                ", args='" + args + '\'' +
                '}';
    }
}
