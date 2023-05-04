package solvd.laba.ermakovich.hu.event;


import com.google.gson.JsonObject;
import lombok.Data;
import lombok.SneakyThrows;
import solvd.laba.ermakovich.hu.aggregate.AggregateRoot;
import solvd.laba.ermakovich.hu.aggregate.AggregateStatus;

/**
 * @author Ermakovich Kseniya
 */
@Data
public final class UpdateDoctor extends EventRoot implements Event {

    public static final String EVENT_TYPE = "UpdateDoctor";
    private AggregateStatus status;

    public UpdateDoctor(final String aggregateId) {
        super(EVENT_TYPE, aggregateId);
    }

    public UpdateDoctor(final String aggregateId,
                        final AggregateStatus status) {
        this(aggregateId);
        this.status = status;
    }
    @Override
    public void copyTo(final AggregateRoot aggregate) {
        aggregate.setStatus(status);
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        JsonObject json = new JsonObject();
        json.addProperty("status", this.status.toString());
        return json.toString();
    }

}
