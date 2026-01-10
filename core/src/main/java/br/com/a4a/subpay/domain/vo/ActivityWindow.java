package br.com.a4a.subpay.domain.vo;

import br.com.a4a.subpay.domain.entity.Activity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Value Object representing a ActivityWindow - Since it would not be wise to always load all
// activities of an account into memory, it olds only a window of the last few days or weeks of activities
public class ActivityWindow {

    private List<Activity> activities;

    public LocalDateTime getStartTimestamp() {
        return this.activities.stream()
                .min(Comparator.comparing(Activity::getTimestamp))
                .orElseThrow(IllegalStateException::new)
                .getTimestamp();
    }

    public LocalDateTime getEndTimestamp() {
        return this.activities.stream()
                .max(Comparator.comparing(Activity::getTimestamp))
                .orElseThrow(IllegalStateException::new)
                .getTimestamp();
    }

    public Money calculateBalance(AccountId accountId) {
        var depositBalance = this.activities.stream()
                .filter(activity -> activity.getTargetAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        var withdrawBalance = this.activities.stream()
                .filter(activity -> activity.getSourceAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        return Money.subtract(depositBalance, withdrawBalance);
    }

    public ActivityWindow(List<Activity> activities) {
        this.activities = activities;
    }

    public ActivityWindow(Activity... activities) {
        this.activities = new ArrayList<>(List.of(activities));
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}
