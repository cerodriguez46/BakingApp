package christopher.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class BakingWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetViewFactory(getApplicationContext());
    }
}
