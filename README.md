# تطبيق أذكار — بنية المشروع

## تشغيل المشروع
هلق المشروع فيه كل ملفات Gradle الأساسية (`build.gradle`, `settings.gradle`, `gradle-wrapper.properties`) — بس افتحه بـ Android Studio وراح يعمل Sync تلقائيًا.

**ملاحظة:** ملف `gradlew`/`gradlew.bat` (سكربتات تشغيل Gradle) مش موجودين بالمجلد. أول ما تفتح المشروع، Android Studio غالبًا رح يطلعلك رسالة تحت زي:
> "Gradle wrapper is not found. Create Gradle wrapper?" أو مشابه لهيك

اضغط **موافقة/Create/OK** ورح يكملها إله لحاله باستخدام Gradle المدمج بالـ IDE. إذا ما طلعت هاي الرسالة والمشروع Sync عادي، فما في داعي لأي شي إضافي.

## بنية الشاشات

| الشاشة | النوع | الملف |
|---|---|---|
| تسجيل الدخول | Activity | LoginActivity + activity_login.xml |
| الرئيسية (تحمل الأربعة تبويبات) | Activity | MainActivity + activity_main.xml |
| اليوم | Fragment | TodayFragment + fragment_today.xml |
| التصنيفات | Fragment | CategoriesFragment + fragment_categories.xml |
| الإحصائيات | Fragment | StatsFragment + fragment_stats.xml |
| الإعدادات | Fragment | SettingsFragment + fragment_settings.xml |
| عرض الاقتباس | Activity | QuoteDetailActivity + activity_quote_detail.xml |
| المساعدة | Activity | HelpActivity + activity_help.xml |

## مسار التنقل
1. **LoginActivity** → يحفظ الاسم بـ SharedPreferences وينقل لـ **MainActivity** (ولو المستخدم مسجل قبل هيك، يدخل عالطول بدون ما يمر باللوجن تاني).
2. **MainActivity** يحتوي BottomNavigationView حقيقي يبدّل بين 4 Fragments (اليوم/التصنيفات/الإحصائيات/الإعدادات) بدون ما يعيد فتح Activity جديدة كل مرة.
3. من **CategoriesFragment**: الضغط على أي كارت يفتح **QuoteDetailActivity** مع تمرير اسم التصنيف.
4. من **SettingsFragment**: الضغط على "المساعدة والتعليمات" يفتح **HelpActivity**.

## وظائف شغالة فعليًا (مو ستاتيك بس)
- **QuoteDetailActivity**: نسخ الاقتباس (Clipboard)، مشاركته (Share Intent)، وحفظه بالمفضلة (SharedPreferences).
- **SettingsFragment**: تبديل الوضع الليلي فعليًا (AppCompatDelegate)، حفظ حالة التنبيهات، وتدوير حجم الخط (صغير/متوسط/كبير).
- **TodayFragment**: زر "اقتباس جديد" يعرض اقتباس عشوائي من قائمة داخلية.

## أخطاء صلحتها بالـ XML الأصلي
- `android:backgroundInt` و `android:backgroundnt` → غير موجودتين، الصح `android:background`
- `@color/avatar_dark` → غير موجود، الصح `@color/avatar_circle_dark`
- بعض الكروت كانت `layout_height="match_parent"` جوا LinearLayout بدل `wrap_content`
- أضفت `progressTint`/`progressBackgroundTint` لشريط التقدم حتى يماشي ألوان التطبيق (افتراضيًا بيطلع أزرق النظام)
