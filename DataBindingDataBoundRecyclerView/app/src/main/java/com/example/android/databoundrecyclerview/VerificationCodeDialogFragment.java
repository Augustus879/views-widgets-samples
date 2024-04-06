package com.example.android.databoundrecyclerview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.DialogFragment;

public class VerificationCodeDialogFragment extends DialogFragment {

    private static final int CODE_LENGTH = 6; // 验证码长度
    private OnVerificationCodeEnteredListener mListener;

    public interface OnVerificationCodeEnteredListener {
        void onVerificationCodeEntered(String code);
    }

    public static VerificationCodeDialogFragment newInstance() {
        return new VerificationCodeDialogFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnVerificationCodeEnteredListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnVerificationCodeEnteredListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_verification_code, container, false);

        // 初始化视图组件
        EditText codeInput = view.findViewById(R.id.code_input);
        TextView codeCount = view.findViewById(R.id.code_count);
        Button submitButton = view.findViewById(R.id.submit_button);

        // 设置验证码输入框长度限制
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(CODE_LENGTH);
        codeInput.setFilters(filters);

        // 实时更新剩余字符数
        codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codeCount.setText(String.format("%d / %d", CODE_LENGTH - s.length(), CODE_LENGTH));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 提交按钮点击事件
        submitButton.setOnClickListener(v -> {
            String enteredCode = codeInput.getText().toString().trim();
            if (enteredCode.length() == CODE_LENGTH) {
                mListener.onVerificationCodeEntered(enteredCode);
                dismiss(); // 关闭对话框
            } else {
                // 显示错误提示或震动等反馈，提示用户输入完整
            }
        });
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 移除标题栏
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }
        }
    }
}