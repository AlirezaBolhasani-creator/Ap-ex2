import java.time.LocalTime;
public class TimeRange
{
    private LocalTime start;
    private LocalTime end;

    public TimeRange(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }
    public void setStart(LocalTime start) {
        this.start = start;
    }
    public void setEnd(LocalTime end) {
        this.end = end;
    }
    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
    public String getTime()
    {
        return start.toString() + ":" + end.toString();
    }
}