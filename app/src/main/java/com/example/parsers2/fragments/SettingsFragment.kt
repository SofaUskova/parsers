package com.example.parsers2.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.CheckBoxPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parsers2.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val settings = context?.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        val editor = settings?.edit()

        val checkMinus: CheckBoxPreference = findPreference("chb1") as CheckBoxPreference
        val checkNumber: CheckBoxPreference = findPreference("chb2") as CheckBoxPreference
        val checkFour: CheckBoxPreference = findPreference("chb3") as CheckBoxPreference
        val checkTags: CheckBoxPreference = findPreference("chb4") as CheckBoxPreference
        val checkLinks: CheckBoxPreference = findPreference("chb5") as CheckBoxPreference

        checkMinus.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, o ->
                if (!checkMinus.isChecked) {
                    editor?.putString("settings", "minus")
                    editor?.commit()
                }
                true
            }

        checkNumber.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, o ->
                if (!checkNumber.isChecked) {
                    editor?.putString("settings", "number")
                    editor?.commit()
                }
                true
            }

        checkFour.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, o ->
                if (!checkFour.isChecked) {
                    editor?.putString("settings", "four")
                    editor?.commit()
                }
                true
            }

        checkTags.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, o ->
                if (!checkTags.isChecked) {
                    editor?.putString("settings", "tags")
                    editor?.commit()
                }
                true
            }

        checkLinks.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, o ->
                if (!checkLinks.isChecked) {
                    editor?.putString("settings", "link")
                    editor?.commit()
                }
                true
            }

    }
}
